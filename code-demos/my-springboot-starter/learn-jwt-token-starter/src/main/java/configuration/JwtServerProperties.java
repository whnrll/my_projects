package configuration;

import lombok.Data;
import util.UserStore;

@Data
public class JwtServerProperties {
    private UserStore userStore;
}
