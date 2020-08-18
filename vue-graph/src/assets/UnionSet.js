export class UnionSet {
  constructor(vertexList) {
    this.arr = [];
    //初始默认所有都是独立的
    for(var index in vertexList) {
      this.arr[vertexList[index]] = vertexList[index];
    }
  }

  //查找祖先
  find(vertex) {
    let arr = this.arr;
    while(arr[vertex] !== vertex) {
      vertex = arr[vertex];
    }
    return arr[vertex];
  }
  //连接x,y
  union(x,y){
    let xFather = this.find(x);
    let yFather = this.find(y);
    if(xFather !== yFather) {
      this.arr[xFather] = yFather;
    }
  }
}

export default {
  UnionSet
}
