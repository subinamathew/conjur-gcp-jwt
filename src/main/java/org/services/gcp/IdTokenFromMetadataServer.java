package org.services.gcp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdTokenCredentials;
import com.google.auth.oauth2.IdTokenProvider;
import com.google.auth.oauth2.IdTokenProvider.Option;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class IdTokenFromMetadataServer {

  public static void main(String[] args) throws IOException, GeneralSecurityException {
    // TODO(Developer): Replace the below variables before running the code.

    // The url or target audience to obtain the ID token for.
    String url = "https://example.com";

    getIdTokenFromMetadataServer(url);
  }

  // Use the Google Cloud metadata server to create an identity token and add it to the
  // HTTP request as part of an Authorization header.
  public static void getIdTokenFromMetadataServer(String url) throws IOException {
    // Construct the GoogleCredentials object which obtains the default configuration from your
    // working environment.
    GoogleCredentials googleCredentials = GoogleCredentials.getApplicationDefault();

    IdTokenCredentials idTokenCredentials =
        IdTokenCredentials.newBuilder()
            .setIdTokenProvider((IdTokenProvider) googleCredentials)
            .setTargetAudience(url)
            // Setting the ID token options.
            .setOptions(Arrays.asList(Option.FORMAT_FULL, Option.LICENSES_TRUE))
            .build();

    // Get the ID token.
    // Once you've obtained the ID token, you can use it to make an authenticated call to the
    // target audience.
    String idToken = idTokenCredentials.refreshAccessToken().getTokenValue();
    System.out.println("Generated ID token.");
  }
}