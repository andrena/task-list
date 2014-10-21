function Task(name) {
  this.name = name;
  this.id = Task._staticGlobalId++;
}
Task._staticGlobalId = 0;

module.exports = Task;
