<template>
  <el-tabs v-model="activeName"
    @tab-click="handleClick">
    <el-tab-pane label="用户管理" name="first">
      <user v-if="isUserAlive"></user>
    </el-tab-pane>
    <el-tab-pane label="元件管理" name="second">
      <elements v-if="isElementAlive"></elements>
    </el-tab-pane>
    <el-tab-pane label="属性管理" name="third">
      <attribute v-if="isAttributeAlive"></attribute>
    </el-tab-pane>
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
  import attribute from './Attribute.vue';
  import elements from './Elements.vue';
  import project from './Project.vue';
  import Bus from "../assets/Bus.js";
  import user from './User.vue';
  export default {
    provide(){
      return {
        reloadElement: this.reloadElement,
        reloadProject: this.reloadProject,
        reloadAttribute: this.reloadAttribute,
        reloadUser: this.reloadUser,
      }
    },
    components: {
      "attribute": attribute,
      "graph": graph,
      "elements": elements,
      "project": project,
      "user": user,
    },
    data() {
      return {
        activeName: 'second',
        isElementAlive: true,
        isProjectAlive: true,
        isAttributeAlive: true,
        isUserAlive: true,
      }
    },
    methods: {
      handleClick(tab, event) {
        // console.log(tab, event);
      },
      reloadElement() {
        this.isElementAlive = false
        this.$nextTick(() => (this.isElementAlive = true))
      },
      reloadProject() {
        this.isProjectAlive = false
        this.$nextTick(() => (this.isProjectAlive = true))
      },
      reloadAttribute() {
        this.isAttributeAlive = false
        this.$nextTick(() => (this.isAttributeAlive = true))
      },
      reloadUser() {
        this.isUserAlive = false
        this.$nextTick(() => (this.isUserAlive = true))
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
