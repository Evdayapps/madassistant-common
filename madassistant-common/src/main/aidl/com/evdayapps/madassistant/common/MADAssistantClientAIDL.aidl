// MADAssistantClientAIDL.aidl
package com.evdayapps.madassistant.common;

import com.evdayapps.madassistant.common.models.handshake.HandshakeResponseModel;

/**
 * AIDL that enables communication from the repository to the client
 */
interface MADAssistantClientAIDL {

    /**
     *
     * @param HandshakeResponseModel
     */
    void onHandshakeResponse(in HandshakeResponseModel data);
}