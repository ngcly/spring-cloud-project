import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/ah/user/info',
    method: 'get'
  })
}

export function logout(data) {
  return request({
    url: '/ah/user/revoke',
    method: 'post',
    data
  })
}
