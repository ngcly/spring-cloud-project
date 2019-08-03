import request from '@/utils/request'

// Eureka
export const eureka = 'http://localhost:8000/'

// Spring Boot Admin
export const admin = 'http://localhost:8003/'

// ZipKin
export const zipkin = 'http://localhost:8004/'

// Swagger2
export const swagger = 'http://localhost:8002/swagger-ui.html'

// Hystrix
export const hystrix = 'http://localhost:8005/hystrix'

export function hi() {
  return request({
    url: '/ot/hi',
    method: 'get'
  })
}
