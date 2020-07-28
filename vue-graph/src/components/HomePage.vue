<template>
  <el-tabs v-model="activeName"
    @tab-click="handleClick">
    <el-tab-pane label="用户管理" name="first">
    </el-tab-pane>
    <el-tab-pane label="元件管理" name="second">
      <user></user>
    </el-tab-pane>
    <el-tab-pane label="属性管理" name="third">属性管理</el-tab-pane>
    <el-tab-pane label="项目管理" name="fourth">项目管理</el-tab-pane>
    <el-tab-pane label="画图" style="width: 100%;height: 100%;">
      <graph style="width: 100%;height: 92vh;"></graph>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
  import graph from './Graph.vue';
  import user from './User.vue';
  export default {
    components: {graph,user},
    data() {
      return {
        activeName: 'second',
      }
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  };
</script>
