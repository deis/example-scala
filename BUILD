target(name='example-scala',
  dependencies=[
    'src/main/scala'
  ]
)

jvm_binary(
  name='bin',
  basename='example-scala',
  main='com.example.HelloWorldServerMain',
  dependencies=[
    ':example-scala'
  ],
  excludes=[
    exclude('org.slf4j', 'slf4j-jdk14'),
    exclude('log4j', 'log4j')
  ]
)
