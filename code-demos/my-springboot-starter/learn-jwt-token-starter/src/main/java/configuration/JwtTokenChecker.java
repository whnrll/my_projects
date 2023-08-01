package configuration;

import util.RawAccessToken;
import util.TokenChecker;
import util.UserStore;

public class JwtTokenChecker implements TokenChecker {
    private UserStore userStore;

    public JwtTokenChecker(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public boolean verify(String token) throws Exception {
        RawAccessToken rawAccessToken = new RawAccessToken(token);
        String issuer = rawAccessToken.getIssuer();
        String cipher = userStore.getCipher(issuer);
        rawAccessToken.verify(cipher);
        return false;
    }
}