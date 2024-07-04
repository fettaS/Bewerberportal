<template>
  <div>
  <Header/>
  <Input/>
</div>
</template>

<script>
import Header from './components/Header.vue'
import Input from './components/Input.vue'

export default {
  meta: {
       title: 'Bewerberportal',
       },
  name: 'App',
  components: {
    Header,
    Input
  }
}
</script>
