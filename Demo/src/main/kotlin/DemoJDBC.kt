import java.sql.DriverManager

class DemoJDBC {
    init {
        /*
        import package
        load and register
        create connection
        create statement
        execute statement
        process the results
        close
        */
        try {
            val url = "jdbc:postgresql://localhost:5433/Demo"
            val userName = "postgres"
            val password = "postgres"
            val connection = DriverManager.getConnection(url, userName, password)

            val statement = connection.createStatement()

            /**Create*/
            val queryCreate = "insert into student values (3,'Arya')"
            val resultCreate = statement.executeUpdate(queryCreate)//execute
            println("Data added $resultCreate")

            /**Read*/
            val queryRead = "select * from student"
            val resultRead = statement.executeQuery(queryRead)
            while (resultRead.next()) {
                val id = resultRead.getInt("sId")
                val name = resultRead.getString("name")
                println("$id $name")
            }
            /**Update*/
            val queryUpdate = "update student set name='NakulEdit' where name='Arya'"
            val resultUpdate = statement.executeUpdate(queryUpdate)
            println("Data updated $resultUpdate")

            /**Delete*/
            val queryDelete = "delete from student where name='nakul'"
            val resultDelete = statement.executeUpdate(queryDelete)
            println("Data delete $resultDelete")


            /**Prepared Statement*/
            val queryCreatePrepared = "insert into student values (?,?)"
            val preparedStatement = connection.prepareStatement(queryCreatePrepared)
            preparedStatement.setInt(1,2)
            preparedStatement.setString(2,"Arya")
            val resultCreatePrepared = preparedStatement.executeUpdate()//execute
            println("Data added Prepared $resultCreatePrepared")

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}