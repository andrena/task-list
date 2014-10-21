Task = require './task'

class TaskService

  ->
    @_projects = {}

  _projects : null
  _taskIdCounter: 0

  addProject: (projectName) ->
    @_projects[projectName] = []

  addTaskToProject: (projectName, taskName) ->
    unless @_projects[projectName]
      throw Error
    task = new Task taskName
    @_projects[projectName].push task
    task.id

  findTaskById: (taskId) ->
    tasks = []
    for projectName of @_projects
      tasks ++= [task for task in @_projects[projectName] when task.id == taskId]
    tasks[0] or null

  findAllProjects: ->
    Object.keys @_projects

  findAllTasksForProject: (projectName) ->
    @_projects[projectName] || null

module.exports = TaskService
