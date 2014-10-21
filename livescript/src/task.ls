class Task

  @_staticGlobalId = 0

  (name) ->
    @name = name
    @id = @@_staticGlobalId++


module.exports = Task
