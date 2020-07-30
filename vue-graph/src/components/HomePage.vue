<template>
  <el-tabs v-model="activeName"
    @tab-click="handleClick">
    <el-tab-pane label="用户管理" name="first">
    </el-tab-pane>
    <el-tab-pane label="元件管理" name="second">
      <user v-if="isUserAlive"></user>
    </el-tab-pane>
    <el-tab-pane label="属性管理" name="third">属性管理</el-tab-pane>
    <el-tab-pane label="项目管理" name="fourth">
      <project v-if="isProjectAlive"></project>
    </el-tab-pane>
    <el-tab-pane label="画图" name="graph" style="width: 100%;height: 100%;">
      <graph style="width: 100%;height: 92vh;"></graph>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
  import graph from './Graph.vue';
  import user from './User.vue';
  import project from './Project.vue';
  import Bus from "../assets/Bus.js";
  export default {
    provide(){
      return {
        reloadUser: this.reloadUser,
        reloadProject: this.reloadProject,
      }
    },
    components: {
      "graph": graph,
      "user": user,
      "project": project,
    },
    data() {
      return {
        activeName: 'second',
        isUserAlive: true,
        isProjectAlive: true,
      }
    },
    methods: {
      handleClick(tab, event) {
        // console.log(tab, event);
      },
      reloadUser() {
        this.isUserAlive = false
        this.$nextTick(() => (this.isUserAlive = true))
      },
      reloadProject() {
        this.isProjectAlive = false
        this.$nextTick(() => (this.isProjectAlive = true))
      },
    },
    mounted() {
      Bus.$on("changeTab",tabName => {
        this.activeName = tabName;
        Bus.$emit("loadModel");
      });
    },
  };
</script>
