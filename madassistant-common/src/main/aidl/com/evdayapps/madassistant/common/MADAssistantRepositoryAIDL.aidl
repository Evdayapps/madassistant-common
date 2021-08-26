// MADAssistantRepositoryAIDL.aidl
package com.evdayapps.madassistant.common;

// Declare any non-default types here with import statements
import com.evdayapps.madassistant.common.models.handshake.HandshakeResponseModel;
import com.evdayapps.madassistant.common.models.transmission.TransmissionModel;
import com.evdayapps.madassistant.common.MADAssistantClientAIDL;

interface MADAssistantRepositoryAIDL {

    /**
     * Initiate the async handshake process
     *
     * @param sdkVersion The version of the common sdk
     * @param clientAIDL An instance of MADAssistantClientAIDL to enable return communications
     */
    void initiateHandshake(int sdkVersion, in MADAssistantClientAIDL clientAIDL);

    long startSession();

    void endSession();

    /**
    * Used by the client to inform the repository that it is about to disonnect
    * Also states the cause via [reason] so that the repository can handle it accordingly
    */
    void disconnect(int code, String message);

    /**
    * Informs the repository that it should attempt to update the application changelog
    **/
    void updatePackageInfo(long timestamp);

    void log(in TransmissionModel data);
}
