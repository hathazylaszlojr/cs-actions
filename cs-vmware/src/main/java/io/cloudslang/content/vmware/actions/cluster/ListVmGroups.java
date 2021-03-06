/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.content.vmware.actions.cluster;

import com.hp.oo.sdk.content.annotations.Action;
import com.hp.oo.sdk.content.annotations.Output;
import com.hp.oo.sdk.content.annotations.Param;
import com.hp.oo.sdk.content.annotations.Response;
import com.hp.oo.sdk.content.plugin.ActionMetadata.MatchType;
import com.hp.oo.sdk.content.plugin.ActionMetadata.ResponseType;
import com.vmware.vim25.ClusterVmGroup;
import io.cloudslang.content.utils.OutputUtilities;
import io.cloudslang.content.vmware.constants.Outputs;
import io.cloudslang.content.vmware.entities.http.HttpInputs;
import io.cloudslang.content.vmware.services.ClusterComputeResourceService;
import io.cloudslang.content.vmware.utils.InputUtils;

import java.util.Map;

import static io.cloudslang.content.constants.OtherValues.COMMA_DELIMITER;
import static io.cloudslang.content.vmware.constants.Inputs.CLUSTER_NAME;
import static io.cloudslang.content.vmware.constants.Inputs.DELIMITER;
import static io.cloudslang.content.vmware.constants.Inputs.HOST;
import static io.cloudslang.content.vmware.constants.Inputs.PASSWORD;
import static io.cloudslang.content.vmware.constants.Inputs.PORT;
import static io.cloudslang.content.vmware.constants.Inputs.PROTOCOL;
import static io.cloudslang.content.vmware.constants.Inputs.TRUST_EVERYONE;
import static io.cloudslang.content.vmware.constants.Inputs.USERNAME;

/**
 * Created by pinteae on 10/5/2016.
 */
public class ListVmGroups {

    /**
     * @param host          VMware host or IP.
     *                      Example: "vc6.subdomain.example.com"
     * @param port          optional - The port to connect through.
     *                      Default Value: "443"
     *                      Examples: "443", "80"
     * @param protocol      optional - The connection protocol.
     *                      Default Value: "https"
     *                      Valid Values: "http", "https"
     * @param username      The VMware username used to connect.
     * @param password      The password associated with "username" input.
     * @param trustEveryone optional - If "true" will allow connections from any host, if "false" the connection will be allowed only using a valid vCenter certificate. Check the: https://pubs.vmware.com/vsphere-50/index.jsp?topic=%2Fcom.vmware.wssdk.dsg.doc_50%2Fsdk_java_development.4.3.html to see how to import a certificate into Java Keystore and https://pubs.vmware.com/vsphere-50/index.jsp?topic=%2Fcom.vmware.wssdk.dsg.doc_50%2Fsdk_sg_server_certificate_Appendix.6.4.html to see how to obtain a valid vCenter certificate
     *                      Default Value: "true"
     * @param clusterName   The name of the cluster.
     * @param delimiter     optional - A separator delimiting the list elements.
     *                      Default value: ","
     * @return
     */
    @Action(name = "List DRS VM Groups",
            outputs = {
                    @Output(Outputs.RETURN_CODE),
                    @Output(Outputs.RETURN_RESULT),
                    @Output(Outputs.EXCEPTION)
            },
            responses = {
                    @Response(text = Outputs.SUCCESS, field = Outputs.RETURN_CODE, value = Outputs.RETURN_CODE_SUCCESS,
                            matchType = MatchType.COMPARE_EQUAL, responseType = ResponseType.RESOLVED),
                    @Response(text = Outputs.FAILURE, field = Outputs.RETURN_CODE, value = Outputs.RETURN_CODE_FAILURE,
                            matchType = MatchType.COMPARE_EQUAL, responseType = ResponseType.ERROR, isOnFail = true)
            })
    public Map<String, String> listVmGroups(@Param(value = HOST, required = true) String host,
                                            @Param(value = PORT) String port,
                                            @Param(value = PROTOCOL) String protocol,
                                            @Param(value = USERNAME, required = true) String username,
                                            @Param(value = PASSWORD, encrypted = true) String password,
                                            @Param(value = TRUST_EVERYONE) String trustEveryone,
                                            @Param(value = CLUSTER_NAME, required = true) String clusterName,
                                            @Param(value = DELIMITER) String delimiter) {
        try {
            HttpInputs httpInputs = new HttpInputs.HttpInputsBuilder()
                    .withHost(host)
                    .withPort(port)
                    .withProtocol(protocol)
                    .withUsername(username)
                    .withPassword(password)
                    .withTrustEveryone(trustEveryone)
                    .build();
            return OutputUtilities.getSuccessResultsMap(
                    new ClusterComputeResourceService().
                            listGroups(httpInputs, clusterName, InputUtils.getDefaultDelimiter(delimiter, COMMA_DELIMITER), ClusterVmGroup.class));
        } catch (Exception ex) {
            return OutputUtilities.getFailureResultsMap(ex);
        }
    }
}
