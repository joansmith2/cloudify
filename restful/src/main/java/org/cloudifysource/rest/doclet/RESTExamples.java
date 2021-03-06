/*******************************************************************************
 * 
 * Copyright (c) 2013 GigaSpaces Technologies Ltd. All rights reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *******************************************************************************/
package org.cloudifysource.rest.doclet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;
import org.cloudifysource.domain.ComputeTemplateHolder;
import org.cloudifysource.domain.cloud.FileTransferModes;
import org.cloudifysource.domain.cloud.RemoteExecutionModes;
import org.cloudifysource.domain.cloud.ScriptLanguages;
import org.cloudifysource.domain.cloud.compute.ComputeTemplate;
import org.cloudifysource.dsl.internal.CloudifyConstants.DeploymentState;
import org.cloudifysource.dsl.internal.DSLUtils;
import org.cloudifysource.dsl.internal.debug.DebugModes;
import org.cloudifysource.dsl.rest.response.AddTemplateResponse;
import org.cloudifysource.dsl.rest.response.AddTemplatesResponse;
import org.cloudifysource.dsl.rest.response.AddTemplatesStatus;
import org.cloudifysource.dsl.rest.response.DeploymentEvent;
import org.cloudifysource.dsl.rest.response.InstanceDescription;
import org.cloudifysource.dsl.rest.response.ServiceDescription;
import org.cloudifysource.dsl.rest.response.ServiceInstanceMetricsData;

/**
 * 
 * @author yael
 * @since 2.6.0
 */
public final class RESTExamples {

	private static final int TIMEOUT_MINUTES_EXAMPLE = 5;
	private static final int MAX_INSTANCE_ID = 5;
	private static final int MACHINE_MEMORY_MB = 1600;

	private RESTExamples() {
	}

	static String getAttribute() {
		return "attributeValue";
	}

	static Map<String, Object> getAttributes() {
		final Map<String, Object> attributesExample = new HashMap<String, Object>();
		attributesExample.put("attr1", "value1");
		attributesExample.put("attr2", "value2");
		attributesExample.put("attr3", "value3");
		return attributesExample;
	}

	static String getAppName() {
		return "petclinic";
	}

	static ServiceInstanceMetricsData getServiceInstanceMetricsData() {
		final Map<String, Object> metricsExample = new HashMap<String, Object>();
		final int id1 = getInstanceId();
		final int id2 = id1 + 1;

		metricsExample.put("metric" + id1, "value" + id1);
		metricsExample.put("metric" + id2, "value" + id2);

		return new ServiceInstanceMetricsData(id1, metricsExample);
	}

	static String getServiceName() {
		return "tomcat";
	}

	static String getAuthGroup() {
		return "myAuthGroup";
	}

	static boolean isDebugAll() {
		return false;
	}

	static String getDebugEvents() {
		return "init,install";
	}

	static String getDebugMode() {
		return DebugModes.ON_ERROR.getName();
	}

	static boolean isSelfHealing() {
		return true;
	}

	static String getServiceFileName() {
		return "myService.groovy";
	}

	static int getTimeoutMinutes() {
		return TIMEOUT_MINUTES_EXAMPLE;
	}

	static String getUploadKey() {
		return UUID.randomUUID().toString();
	}

	static String getDeploymentID() {
		return UUID.randomUUID().toString();
	}

	static List<ServiceInstanceMetricsData> getServiceInstanceMetricsDataList() {
		final List<ServiceInstanceMetricsData> list = new LinkedList<ServiceInstanceMetricsData>();
		list.add(getServiceInstanceMetricsData());
		list.add(getServiceInstanceMetricsData());
		return list;
	}

	static List<String> getInstanceNames() {
		final List<String> instanceNames = new ArrayList<String>();
		instanceNames.add(getInstanceName(null, null));
		instanceNames.add(getInstanceName(null, null));
		instanceNames.add(getInstanceName(null, null));
		return instanceNames;
	}

	static int getNumberOfInstances() {
		return getInstanceId() + 1;
	}

	static List<DeploymentEvent> getEvents() {
		final List<DeploymentEvent> events = new LinkedList<DeploymentEvent>();

		final DeploymentEvent event1 = new DeploymentEvent();
		event1.setDescription("[127.0.0.1/127.0.0.1] - tomcat-1 INIT invoked");
		event1.setIndex(0);
		final DeploymentEvent event2 = new DeploymentEvent();
		event2.setDescription("[127.0.0.1/127.0.0.1] - tomcat-1 INIT completed, duration: 4.2 seconds");
		event2.setIndex(1);

		events.add(event1);
		events.add(event2);

		return events;
	}

	static DeploymentState getApplicationState() {
		return DeploymentState.STARTED;
	}

	static List<ServiceDescription> getServicesDescription() {
		final List<ServiceDescription> list = new LinkedList<ServiceDescription>();
		final int id1 = getInstanceId();
		list.add(getServiceDescription("service" + id1, id1));
		final int id2 = id1 + 1;
		list.add(getServiceDescription("service" + id2, id2));
		return list;
	}

	static String getAuthGroups() {
		return getAuthGroup() + "1," + getAuthGroup() + "2," + getAuthGroup() + "3";
	}

	static String getInstanceName(final String serviceName, final String appName) {
		final String effServiceName = serviceName != null ? serviceName : getServiceName();
		final String effAppName = appName != null ? appName : getAppName();
		return effAppName + "." + effServiceName;
	}

	
	static int getInstanceId() {
		return getRandomInt(MAX_INSTANCE_ID);
	}

	static String getHardwareId() {
		return "localcloud";
	}

	static String getImageId() {
		return "localcloud";
	}

	static String getMachineId() {
		return "localcloud";
	}

	static String getPrivateIp() {
		return getLocalHost().getHostAddress();
	}

	static Map<String, Object> getProcessDetails(final int instanceId) {
		final Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", "icon.png");
			map.put("url", null);
			map.put("Cloud Public IP", getPublicIp());
			map.put("Cloud Image ID", getImageId());
			map.put("Cloud Private IP", getPrivateIp());
			map.put("GSC PID", "4276");
			map.put("Cloud Hardware ID", getHardwareId()); 
			map.put("Instance ID", instanceId); 
			map.put("Machine ID", getMachineId());
			map.put("Working Directory", 
					"D:\\gigaSpaces\\gigaspaces-cloudify\\work\\processing-units\\simpleApp_simple_" 
							+ instanceId + "_1051025036\\ext");

		
		return map;
	}

	static String getPublicIp() {
		String localhostaddress = getLocalHost().getHostAddress();
		localhostaddress = localhostaddress.substring(0, localhostaddress.length() - 1);
		Random random = new Random();
		localhostaddress = localhostaddress.concat(String.valueOf(random.nextInt(10)));
		return localhostaddress;
	}

	static String getTemplateName() {
		return "MY_TEMPLATE_" + getInstanceId();
	}

	static ComputeTemplateHolder getTemplate() {
		ComputeTemplateHolder holder = new ComputeTemplateHolder();
		String templateName = getTemplateName();
		holder.setName(templateName);
		holder.setTemplateFileName(templateName + DSLUtils.TEMPLATE_DSL_FILE_NAME_SUFFIX);
		holder.setPropertiesFileName(templateName + DSLUtils.TEMPLATES_PROPERTIES_FILE_NAME_SUFFIX);
		ComputeTemplate cloudTemplate = new ComputeTemplate();
		cloudTemplate.setImageId("eu-west-1/ami-c1aaabb5");
		cloudTemplate.setMachineMemoryMB(MACHINE_MEMORY_MB);
		cloudTemplate.setHardwareId("m1.small");
		cloudTemplate.setLocationId("eu-west-1");
		cloudTemplate.setNumberOfCores(1);
		cloudTemplate.setRemoteDirectory("upload");
		cloudTemplate.setLocalDirectory("upload");
		cloudTemplate.setScriptLanguage(ScriptLanguages.LINUX_SHELL);
		cloudTemplate.setFileTransfer(FileTransferModes.SFTP);
		cloudTemplate.setKeyFile("key-file.pem");
		cloudTemplate.setUsername(templateName.toLowerCase());
		cloudTemplate.setRemoteExecution(RemoteExecutionModes.SSH);
		cloudTemplate.setPrivileged(true);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("keyPair", "key-pair-eu");
		String[] securityGroups = {"default"};
		options.put("securityGroups", securityGroups);
		cloudTemplate.setOptions(options);
		holder.setCloudTemplate(cloudTemplate);
		return holder;
	}
	
	static AddTemplatesResponse getAddTemplatesResponseExample() {
		// creating partial failure example
		Map<String, AddTemplateResponse> templates = new HashMap<String, AddTemplateResponse>();
		String instance1 = RESTExamples.getPublicIp();
		String instance2 = RESTExamples.getPublicIp();
		String instance3 = RESTExamples.getPublicIp();
		List<String> instances = new LinkedList<String>();
		instances.add(instance1);
		instances.add(instance2);
		instances.add(instance3);
		/*
		 * template1 (failure)
		 */
		AddTemplateResponse template1Response = new AddTemplateResponse();
		Map<String, String> template1FailureMap = new HashMap<String, String>();
		template1FailureMap.put(instance1, "template already exists");
		template1FailureMap.put(instance2, "template already exists");
		template1FailureMap.put(instance3, "template already exists");
		template1Response.setFailedToAddHosts(template1FailureMap);
		List<String> template1SuccessList = new LinkedList<String>();
		template1Response.setSuccessfullyAddedHosts(template1SuccessList);
		templates.put("SMALL_LINUX", template1Response);
		/*
		 * template2 (partial failure)
		 */
		AddTemplateResponse template2Response = new AddTemplateResponse();
		Map<String, String> template2FailureMap = new HashMap<String, String>();
		template2FailureMap.put(instance1, "template already exists");
		template2FailureMap.put(instance2, "template already exists");
		List<String> template2SuccessList = new LinkedList<String>();
		template2Response.setFailedToAddHosts(template2FailureMap);
		template2SuccessList.add(instance3);
		template2Response.setSuccessfullyAddedHosts(template2SuccessList);
		templates.put("SMALL_UBUNTU", template2Response);
		/*
		 * template3 (success)
		 */
		AddTemplateResponse template3Response = new AddTemplateResponse();
		Map<String, String> template3FailureMap = new HashMap<String, String>();
		template3Response.setFailedToAddHosts(template3FailureMap);
		List<String> template3SuccessList = instances;
		template3Response.setSuccessfullyAddedHosts(template3SuccessList);
		templates.put("SMALL_SUSE", template3Response);
		
		AddTemplatesResponse response = new AddTemplatesResponse();
		response.setTemplates(templates);
		response.setInstances(instances);
		response.setStatus(AddTemplatesStatus.PARTIAL_FAILURE);
		return response;
	}
	
	private static int getRandomInt(final int max) {
		return RandomUtils.nextInt(max);
	}

	private static ServiceDescription getServiceDescription(final String serviceName, final int id) {
		final ServiceDescription serviceDescription = new ServiceDescription();

		final int numberOfInstances = getNumberOfInstances();
		final List<InstanceDescription> instancesDescriptionList = new LinkedList<InstanceDescription>();
		instancesDescriptionList.add(getInstanceDescription(serviceName, id));
		instancesDescriptionList.add(getInstanceDescription(serviceName, id + 2));

		serviceDescription.setApplicationName(getAppName());
		serviceDescription.setInstanceCount(numberOfInstances);
		serviceDescription.setPlannedInstances(numberOfInstances);
		serviceDescription.setServiceName(serviceName);
		serviceDescription.setInstancesDescription(instancesDescriptionList);
		serviceDescription.setServiceState(DeploymentState.STARTED);

		return serviceDescription;
	}

	private static InstanceDescription getInstanceDescription(final String serviceName, final int id) {
		final InstanceDescription instanceDescription = new InstanceDescription();

		final InetAddress localHost = getLocalHost();
		String hostAddress;
		String hostName;
		if (localHost == null) {
			hostAddress = "localhost";
			hostName = "localhost";
		} else {
			hostAddress = localHost.getHostAddress();
			hostName = localHost.getHostName();
		}

		instanceDescription.setHostAddress(hostAddress);
		instanceDescription.setHostName(hostName);
		instanceDescription.setInstanceId(id);
		instanceDescription.setInstanceName(getInstanceName(serviceName, null));
		instanceDescription.setInstanceStatus("RUNNING");

		return instanceDescription;
	}

	private static InetAddress getLocalHost() {
		try {
			return InetAddress.getLocalHost();
		} catch (final UnknownHostException e) {
			return null;
		}
	}
}
