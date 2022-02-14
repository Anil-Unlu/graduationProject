Graduation Project

Proje katmanlı mimari ile oluşutruldu.Projede redis swagger exception handling postgresql spring hibernate ve testler yapılmıştır.Restendpointleri 

<http://localhost:8080/user>

<http://localhost:8080/users>  dır.

User endpointinde crud işlemleri gerçekleşmektedir.

Users endpointinde identitye göre credit sonuç sorgulaması atılabilir.

İdentityler kişiye özel olmalıdır.

retrieveUser ve updateUser functionlarında redis kullanılmıştır.

Testler için integration testler ve unit testler yapılmıştır.

Credit hesaplaması UserServiceImpl. Katmanında yapılmıştır.

Projeyi çalıştırmak

Öncelikle infra-up.sh fileı çalıştırılmalıdır.Redis-commander için 8081 portundan cacheleme kontrolü yapılabilir.

Swaggera bakılarak istekler postmanden atılabilir.

Swagger için url: http://localhost:8080/swagger-ui/#/
