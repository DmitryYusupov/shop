package ru.yusdm.shop.common;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("integration-test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BaseIntegrationTest {

}
