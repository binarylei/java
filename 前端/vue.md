# vue

## 安装

```
# 最新稳定版
$ cnpm install vue

# 全局安装 vue-cli
$ cnpm install -g vue-cli

# 创建一个基于 webpack 模板的新项目
$ vue init webpack my-project
```

## 父子组件

### 局部组件

```
<template>
  <div id="app">
    <header-vue a="a"></header-vue>
    <Body></Body>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Body from '@/components/Body'
import Footer from '@/components/Footer'

export default {
  name: 'app',
  components: { headerVue:  Header, Body, Footer }
}
</script>
```

### 全局组件

在 `main.js`