package com.mindtree.customer.config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= SwaggerConfig.class)
public class SwaggerConfigTest {
   @Autowired
   Docket docket;

   @Autowired
   UiConfiguration uiconfiguration;
   @Test
   public void docketTest() {
      assertNotNull(docket);
   }
   @Test
   public void uiconfigurationTest() {
      assertNotNull(uiconfiguration);
   }
}