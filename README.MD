<!-- #######  THIS IS A COMMENT - Visible only in the source editor #########-->
<p><strong>Приветствуем вас на проекте camel-конектор!</strong></p>
<p><strong>1. Профили</strong></p>
<p>По умолчанию свойства пишутся в application.properties<br />Можно сделать профили, в которых необходимые свойства можно переопределить (не нужно переписывать все свойства, а только необходимые)<br />Для создания профиля необходимо создать application-{profile}.properties (без фигурных скобок), где profile - это имя профиля</p>
<p>Для того что бы использовать профиль (профили) необходимо задать переменную&nbsp;spring.profiles.active Это можно делать двумя способами:<br /><strong>1)</strong> путем передачи названия профиля через параметры JVM<br />$ java -jar -Dspring.profiles.active=production demo.jar<br /><strong>2)</strong> путем передачи названия профиля в application.properties<br />spring.profiles.active=production, mysql</p>
<p>Причем стоит заметить, что определение свойства через параметры JVM имеет больший вес и переопределит любое свойство (в том числе application.properties), переданное через application.properties<br /><br /></p>
<p><strong>2. Docker</strong></p>
<p>Для запуска коннектора локально неплохо иметь локальный IBM MQ и Kafka broker. Их удобно запускать с помощью Docker</p>
<p><strong>1.</strong> Запуск IBM MQ (проект&nbsp;<a href="https://github.com/ibm-messaging/mq-container/blob/master/docs/usage.md">https://github.com/ibm-messaging/mq-container/blob/master/docs/usage.md</a>) делается например так:<br />docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --publish 1414:1414 --publish 9443:9443 ibmcom/mq</p>
<p>после этого запустися сервер IBM MQ с переданными параметрами. Также будем доступна веб-консоль для управления брокером по адресу:<br /><a href="https://127.0.0.1:9443/ibmmq/console/login.html">https://127.0.0.1:9443/ibmmq/console/login.html</a><br />учетка\пароль: admin\passw0rd</p>
<p><strong>2.</strong> Запуск Kafka брокера (проект&nbsp;https://github.com/wurstmeister/kafka-docker) делается например так:<br />- устанавливается docker-compose<br />- импортируется посредством git в любую папку проект&nbsp;<a href="https://github.com/wurstmeister/kafka-docker">https://github.com/wurstmeister/kafka-docker</a><br />- настраиваются параметры в файле&nbsp;docker-compose.yml лежащем в корне.<br />- из терминала запщенного из этой же директории делается sudo&nbsp;<code>docker-compose up -d. Броке запущен. Для остановки используется команда&nbsp;</code><code>docker-compose stop.</code></p>
<p>В итоге запустится сервер Kafka брокер&nbsp;с переданными параметрами. Подключиться холрошо получается с помощью приложения kafkatool</p>
<p>&nbsp;</p>
<p><strong>2. Naming Convention</strong></p>
<p>2.1 Kafka</p>
<p>Для кафка топиков предлагается использовать наименования в формате:<br />&nbsp; <em>продюсер . консюмер&nbsp;. система_отправитель&nbsp;. система_приемник . среда_использования</em> <em>. формат_данных&nbsp;</em></p>
<div>Допустимые значения:</div>
<div>&nbsp;- Для продюсера (camel; esp)</div>
<div>&nbsp;- Для консюмера&nbsp;(dlq; storage; esp; camel)</div>
<div>&nbsp;- Для система_отправитель&nbsp;(af, mc, arm, vabank)</div>
<div>&nbsp;- Для система_приемник&nbsp;(af, mc, arm, vabank)</div>
<div>&nbsp;- Для формат_данных (json, xml)</div>
<div>&nbsp;- Для среда использования (dev, model, &lt;прочее, например petrov_test&gt;)</div>
<div>&nbsp;</div>
<div>Примеры:</div>
<div>
<div># расшифровка: камел ложит на хренение сообщение от арм в антифрод для среды dev</div>
<div>camel.storage.arm.af.dev.xml</div>
<div>.............</div>
<div>camel.dlq.arm.af.dev.xml</div>
<div>............</div>
<div>camel.esp.arm.af.model.json</div>
<div>............</div>
<div># расшифровка: esp ложит для камела сообщение от антифрода в арм в формате json для среды dev</div>
<div>esp.camel.af.arm.dev.json</div>
<div>camel.storage.af.arm.dev.xml</div>
<div>......</div>
<div>camel.storage.mc.af.dev.xml</div>
<div>camel.dlq.mc.af.dev.xml</div>
<div>camel.esp.mc.af.dev.json</div>
<div>esp.camel.af.mc.dev.json</div>
<div>camel.storage.af.mc.dev.xml</div>
<div>....</div>
<div># расшифровка: есп ложит для себя же результат сценария 4 для всех в формате json для среды dev</div>
<div>esp.esp.scenario4-to-all.dev.xml</div>
<div>esp.esp.mc_extended.all.dev.json</div>
</div>