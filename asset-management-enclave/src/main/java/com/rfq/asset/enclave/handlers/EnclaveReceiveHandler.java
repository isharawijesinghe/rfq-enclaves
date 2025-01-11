package com.rfq.asset.enclave.handlers;

import com.rfq.common.enums.Actions;
import com.rfq.common.requests.AssetManagementRequest;
import com.rfq.common.responses.AssetManagementResponse;
import com.rfq.enclave.enclave.handler.AbstractActionHandler;
import com.rfq.enclave.enclave.kms.KMSClient;
import com.rfq.enclave.enclave.nsm.NsmClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class EnclaveReceiveHandler extends AbstractActionHandler<AssetManagementRequest, AssetManagementResponse> {

    private final Logger logger = LogManager.getLogger(EnclaveReceiveHandler.class);

    private final NsmClient nsmClient;

    private final KMSClient kmsClient;

    public EnclaveReceiveHandler(NsmClient nsmClient, KMSClient kmsClient){
        this.nsmClient = nsmClient;
        this.kmsClient = kmsClient;
    }

    @Override
    public boolean canHandle(String action) {
        return Actions.ECHO.name().equalsIgnoreCase(action);
    }

    @Override
    public AssetManagementResponse handle(AssetManagementRequest assetManagementRequest) {
        String nsmModuleId = this.nsmClient.describeNsm().getModuleId();
        AssetManagementResponse result = new AssetManagementResponse();
        String hiiii = kmsClient.decrypt(assetManagementRequest.getAwsCredentials(), assetManagementRequest.getValue());
        System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii encryptttttttttttt " + hiiii + " kms client " + kmsClient);
        result.setValue("Echo from Enclave " + nsmModuleId + ": " + assetManagementRequest.getValue() + " encrypted data " + hiiii);
        return result;
    }
}
