package fr.univartois.ili.fsnet.auth;

import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.univartois.ili.fsnet.entities.SocialEntity;

/**
 * This filter is used to make sure that the current request is made by an
 * authenticated user
 * 
 * @author Mathieu Boniface < mat.boniface {At} gmail.com >
 */
public class IsAuthenticatedFilter implements Filter {

	private static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("fsnetjpa");

	private ServletContext servletContext;

	/**
	 * Verify if the current user is authenticated
	 */
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		HttpSession session;
		SocialEntity es;
		RequestDispatcher dispatch;

		session = ((HttpServletRequest) request).getSession(); // NOSONAR
		es = (SocialEntity) session
				.getAttribute(Authenticate.AUTHENTICATED_USER);

		if (es == null) {
			dispatch = servletContext
					.getRequestDispatcher(Authenticate.WELCOME_NON_AUTHENTICATED_PAGE);
			dispatch.forward(request, response);
		} else {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			es = em.find(SocialEntity.class, es.getId());
			es.setLastConnection(new Date());
			em.merge(es);
			em.getTransaction().commit();
			em.close();
			chain.doFilter(request, response);
		}
	}

	/**
	 * Init method used to retrieve Servlet Context
	 */
	public void init(final FilterConfig filterConfig) throws ServletException {
		servletContext = filterConfig.getServletContext();
	}

	@Override
	public void destroy() {
	}
}
