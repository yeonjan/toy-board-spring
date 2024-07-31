package toy.board.global.auth.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import toy.board.model.entity.Member;

import java.util.Map;

@Getter
public class OAuth2Attributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String name,
                            String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName,
                                      Map<String, Object> attributes) {

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName,
                                             Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public Member toEntity() {
        return Member.of(name, email, picture);
    }
}