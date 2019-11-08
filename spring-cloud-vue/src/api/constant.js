import request from '@/utils/request'

// nacos
export const nacos = 'http://localhost:8848/nacos'

// Spring Boot Admin
export const admin = 'http://localhost:8003/'

// ZipKin
export const zipkin = 'http://localhost:9411/'

// Swagger2
export const swagger = 'http://localhost:8002/swagger-ui.html'

// sentinel
export const sentinel = 'http://localhost:8088'

export function hi() {
  return request({
    url: '/user/hey',
    method: 'get'
  })
}
