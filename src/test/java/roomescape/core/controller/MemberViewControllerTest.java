package roomescape.core.controller;

import static org.springframework.restdocs.cookies.CookieDocumentation.cookieWithName;
import static org.springframework.restdocs.cookies.CookieDocumentation.requestCookies;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import roomescape.core.dto.auth.TokenRequest;
import roomescape.utils.TestFixture;

@AcceptanceTest
public class MemberViewControllerTest {
    @LocalServerPort
    private int port;

    private RequestSpecification spec;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        spec = new RequestSpecBuilder().addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("예약 페이지로 이동한다.")
    void moveToReservationPage() {
        RestAssured.given(spec).log().all()
                .accept("application/json")
                .filter(document("page/reservation/",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
                .when().get("/reservation")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("로그인 페이지로 이동한다.")
    void moveToLoginPage() {
        RestAssured.given(spec).log().all()
                .accept("application/json")
                .filter(document("page/login/",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
                .when().get("/login")
                .then().log().all()
                .statusCode(200);
    }


    @Test
    @DisplayName("회원 가입 페이지로 이동한다.")
    void moveToSignupPage() {
        RestAssured.given(spec).log().all()
                .accept("application/json")
                .filter(document("page/signup/",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
                .when().get("/signup")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("로그인된 회원의 예약 목록 조회 페이지로 이동한다.")
    void findMyReservation() {
        String accessToken = RestAssured
                .given().log().all()
                .body(new TokenRequest(TestFixture.getAdminEmail(), TestFixture.getPassword()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/login")
                .then().log().cookies().extract().cookie("token");

        RestAssured.given(spec).log().all()
                .cookie("token", accessToken)
                .accept("application/json")
                .filter(document("page/reservation-mine/",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        requestCookies(cookieWithName("token").description("로그인한 회원의 토큰"))))
                .when().get("/reservation-mine")
                .then().log().all()
                .statusCode(200);
    }
}