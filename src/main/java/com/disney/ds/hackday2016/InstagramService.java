package com.disney.ds.hackday2016;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstagramService {
	

	public InstagramService() throws OAuthSystemException{
		
		String token="";
        try {
            OAuthClientRequest request = OAuthClientRequest
                    .tokenLocation("https://api.instagram.com/oauth/authorize")
                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
                    .setClientId("fcb9fc3f96004112acca7240baf8636e")
                    .setClientSecret("fc0cfc3c8ae74254843f13aecd47fd03")
                    .buildBodyMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthResourceResponse response = oAuthClient.resource(request, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
            System.out.println("response body: " + response.getBody());
            //JSONObject json = JSONUtils.fromString(response.getBody());
            //token = json.get("access_token").toString();
        } catch (OAuthProblemException e) {
            System.out.println("OAuth error: " + e.getError());
            System.out.println("OAuth error description: " + e.getDescription());
        }
		
	}
	
	
	public String getInstagramData() throws Exception {
		
		return null;
		
	}

}
