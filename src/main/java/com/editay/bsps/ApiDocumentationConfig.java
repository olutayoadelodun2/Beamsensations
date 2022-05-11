package com.editay.bsps;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.SwaggerDefinition.Scheme;

@SwaggerDefinition(
   info = @Info(
   description = "Awesome Resources",
   version = "V12.0.12",
   title = "Car Dealers Resource API",
   contact = @Contact(
   name = "Olutayo Adelodun",
   email = "olutayopeter2014@gmail.com",
   url = "http://beamsensations.com"
),
   license = @License(
   name = "Apache 2.0",
   url = "http://www.apache.org/licenses/LICENSE-2.0"
)
),
   consumes = {"application/json", "application/xml"},
   produces = {"application/json", "application/xml"},
   schemes = {Scheme.HTTP, Scheme.HTTPS},
   externalDocs = @ExternalDocs(
   value = "Read This For Sure",
   url = "http://in28minutes.com"
)
)
public interface ApiDocumentationConfig {
}
