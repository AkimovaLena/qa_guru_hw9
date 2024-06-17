import com.fasterxml.jackson.core.JsonFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Offer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import static model.State.VERIFICATION;
import static org.assertj.core.api.Assertions.assertThat;

public class JSONValidaterTest {
    private ClassLoader cl = ZipvalidatorTetst.class.getClassLoader();

    @Test
    @DisplayName("Чтение и проверка содержимого JSON")
    void jsonValidatorTest() throws Exception {

        try (InputStream json = cl.getResourceAsStream("offer.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Offer offer = objectMapper.readValue(json, Offer.class);
            assertThat(offer.getId()).isEqualTo("7e231955-62c4-4142-ab37-146357275bc6");
            assertThat(offer.getState()).isEqualTo(VERIFICATION);
            assertThat(offer.getBus().length).isEqualTo(2);
            assertThat(offer.getBus()[0].getId()).isEqualTo("d145088a-bee3-4dd9-b9a2-d5d5aaa01808");
            assertThat(offer.getBus()[1].getId()).isEqualTo("ed1821ac-587f-4a89-94bb-c49ac0579c1d");
        }

    }
}
