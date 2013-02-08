package fr.univartois.ili.fsnet.entities.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 
 * @author romuald druelle
 * @author SAID Mohamed <simo.said09 at gmail.com>
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ MemberCVTest.class,AddressTest.class, AnnouncementTest.class,
		CommunityTest.class, ConsultationChoiceTest.class, HubTest.class,
		InteractionTest.class, InterestTest.class, MeetingTest.class, ProfileVisitTest.class,
		SocialEntityTest.class,DegreeCvTest.class, SocialGroupTest.class,InteractionRolePKTest.class, TopicTest.class, PrivateMessageTest.class , AssociationDateDegreeCVTest.class
		,InteractionGroupsTest.class})
public class AllTests {
}
