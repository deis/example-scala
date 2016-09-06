scala_library(
  name='scala',
  dependencies=[
    '3rdparty/jvm/ch/qos/logback:logback-classic',
    'finatra/slf4j',
    'finatra/http',
  ],
  excludes = [
    exclude(org='org.slf4j', name='slf4j-jdk14')
  ],
  fatal_warnings=True,
  sources=rglobs('*.scala'),
  resources=[
    'src/main/resources'
  ]
)
