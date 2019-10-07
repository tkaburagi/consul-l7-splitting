kind = "service-router"
name = "greetings-api"
routes = [
  {
    match {
      http {
        header = [
          {
            name  = "canary"
            exact = "true"
          },
        ]
      }
    }

    destination {
      service = "greetings-api"
      service_subset = "v2"
    }

  },
]
