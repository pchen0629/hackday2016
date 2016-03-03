package com.disney.ds.hackday2016;

import org.springframework.stereotype.Component;

@Component
public class InstagramService {
	
//
//	public InstagramService() throws OAuthSystemException{
//
//		String token="";
//        try {
//            OAuthClientRequest request = OAuthClientRequest
//                    .authorizationProvider(OAuthProviderType.INSTAGRAM)
//                    .setRedirectURI("http://disney.com")
//                    .setResponseType(OAuth.OAUTH_CODE)
//                    .setScope("public_content")
//                    .setClientId("08e2adf710fc4c19a44d97b4284f5136")
//                    .buildBodyMessage();
//            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//            OAuthResourceResponse response = oAuthClient.resource(request, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
//            System.out.println("response body: " + response.getBody());
//            //JSONObject json = JSONUtils.fromString(response.getBody());
//            //token = json.get("access_token").toString();
//        } catch (OAuthProblemException e) {
//            System.out.println("OAuth error: " + e.getError());
//            System.out.println("OAuth error description: " + e.getDescription());
//        }
//
//	}
//
//
//	public String getInstagramData() throws Exception {
//
//		return null;
//
//	}
//
//    public static void main(String args[]) throws Exception{
//        InstagramService a = new InstagramService();
//        a.getInstagramData();
//
//    }

}
