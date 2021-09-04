package net.zerotodev.api.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id private String id;
    private String alias;
    private String name;
    private String email;
}
