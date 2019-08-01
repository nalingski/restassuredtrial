import config.TestBaseClass;
import io.restassured.response.Response;
import org.junit.Test;
import com.ttc.mbie.ecli.models.Catalogue;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class SampleRestTest extends TestBaseClass {


    @Test
    public void TestCatalogueNameIsCarbonCredits () {
        // Get response for Carbon Credits Catalogue
        Response response =
                given().
                        spec(trademeSandbox_requestSpec).
                when().
                        get("Categories/6327/Details.json?catalogue=false ").
                then().
                        statusCode(200).
                        // log().all(). // log to view or debug response
                        and().extract().response();

        Catalogue catalogue = response.as(Catalogue.class);

        // Assert Test
        assertThat(catalogue.getName()).as("Verify Catalogue Name is \"Carbon credits\"").isEqualTo("Carbon credits");
    }

    @Test
    public void TestCatalogueCanRelistIsTrue () {
        // Get response for Carbon Credits Catalogue
        Response response =
                given().
                        spec(trademeSandbox_requestSpec).
                when().
                        get("Categories/6327/Details.json?catalogue=false ").
                then().
                        statusCode(200).
                        and().extract().response();

        Catalogue catalogue = response.as(Catalogue.class);

        // Assert Test
        assertThat(catalogue.getCanRelist()).as("Verify Catalogue CanRelist value is true").isTrue();
    }

    @Test
    public void TestCataloguePromotionGalleryDescriptionContainsText2xLargerImage() {
        // Get response for Carbon Credits Catalogue
        Response response =
                given().
                        spec(trademeSandbox_requestSpec).
                when().
                        get("Categories/6327/Details.json?catalogue=false ").
                then().
                        statusCode(200).
                        and().extract().response();

        Catalogue catalogue = response.as(Catalogue.class);

        long count = catalogue.getPromotions().stream().
                filter(p -> p.getDescription().contains("2x larger image") &&
                            p.getName().contains("Gallery")
                        )
                .count();

        // Assert Test
        assertThat(count).as("Verify Catalogue has Promotion named \"Gallery\" that contains text \"2x larger image\"").isGreaterThanOrEqualTo(1);
    }
}
