db.createUser(
    {
        user:"richard",
        pwd:"Rsug3101",
        roles:[{
            role:"readWrite",
            db:"RoundRobinLogsService"
        }]
    }
)
