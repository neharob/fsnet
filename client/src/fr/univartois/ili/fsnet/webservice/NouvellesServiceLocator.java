/**
 * NouvellesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.univartois.ili.fsnet.webservice;

public class NouvellesServiceLocator extends org.apache.axis.client.Service implements fr.univartois.ili.fsnet.webservice.NouvellesService {

    public NouvellesServiceLocator() {
    }


    public NouvellesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NouvellesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NouvellesInformationsPort
    private java.lang.String NouvellesInformationsPort_address = "http://localhost:8080/fr.univartois.ili.fsnet.webservice/NouvellesService";

    public java.lang.String getNouvellesInformationsPortAddress() {
        return NouvellesInformationsPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NouvellesInformationsPortWSDDServiceName = "NouvellesInformationsPort";

    public java.lang.String getNouvellesInformationsPortWSDDServiceName() {
        return NouvellesInformationsPortWSDDServiceName;
    }

    public void setNouvellesInformationsPortWSDDServiceName(java.lang.String name) {
        NouvellesInformationsPortWSDDServiceName = name;
    }

    public fr.univartois.ili.fsnet.webservice.NouvellesInformations getNouvellesInformationsPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NouvellesInformationsPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNouvellesInformationsPort(endpoint);
    }

    public fr.univartois.ili.fsnet.webservice.NouvellesInformations getNouvellesInformationsPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fr.univartois.ili.fsnet.webservice.NouvellesInformationsPortBindingStub _stub = new fr.univartois.ili.fsnet.webservice.NouvellesInformationsPortBindingStub(portAddress, this);
            _stub.setPortName(getNouvellesInformationsPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNouvellesInformationsPortEndpointAddress(java.lang.String address) {
        NouvellesInformationsPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fr.univartois.ili.fsnet.webservice.NouvellesInformations.class.isAssignableFrom(serviceEndpointInterface)) {
                fr.univartois.ili.fsnet.webservice.NouvellesInformationsPortBindingStub _stub = new fr.univartois.ili.fsnet.webservice.NouvellesInformationsPortBindingStub(new java.net.URL(NouvellesInformationsPort_address), this);
                _stub.setPortName(getNouvellesInformationsPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("NouvellesInformationsPort".equals(inputPortName)) {
            return getNouvellesInformationsPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.fsnet.ili.univartois.fr/", "NouvellesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.fsnet.ili.univartois.fr/", "NouvellesInformationsPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NouvellesInformationsPort".equals(portName)) {
            setNouvellesInformationsPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}