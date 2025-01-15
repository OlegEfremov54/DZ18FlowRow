package com.example.dz18flowrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dz18flowrow.ui.theme.DZ18FlowRowTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DZ18FlowRowTheme {
                FlowColumn(
                    maxItemsInEachColumn = 3,
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    employeeList
                        .sortedWith (
                            compareBy<Employee> {it.prof}
                                .thenBy { it.firstName }
                        ).forEach {
                            EmployeeItem(it)
                        }
                }

            }
        }
    }
}

private val employeeList = listOf(
    Employee("Вова", "Петров", "программист", 150000, R.drawable.flaffi),
    Employee("Миша", "Сидоров", "инженер", 100000, R.drawable.konung1),
    Employee("Гриша", "Семечкин", "инженер", 120000, R.drawable.konung2),
    Employee("Валя", "Растрапович", "инженер", 128000, R.drawable.konung3),
    Employee("Галя", "Милкина", "врач", 300000, R.drawable.konung4),
    Employee("Петя", "Иванов", "врач", 180000, R.drawable.konung5),
    Employee("Света", "Лазуткина", "врач", 150000, R.drawable.flaffi),
    Employee("Бармалей", "Разбойников", "программист", 500000, R.drawable.konung1),
    Employee("Айболит", "Хирургович", "преподаватель", 650000, R.drawable.konung2),
    Employee("Кинг", "Конг", "преподаватель", 350000, R.drawable.konung3),
    Employee("Айс", "Вентура", "программист", 250000, R.drawable.orig),
    Employee("Рита", "Зеленая", "преподаватель", 1000000,R.drawable.sl),
)

data class Employee(
    val firstName: String,
    val lastName: String,
    var prof: String,
    var salary: Int,
    var photo : Int = R.mipmap.ic_launcher_round,
)

@Composable
fun EmployeeItem(employee: Employee) {
    Column(
        modifier = Modifier
            .width(360.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(2.dp, Color.DarkGray, shape = RoundedCornerShape(24.dp))
            .background(Color.LightGray)
            .padding(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(employee.photo),
                contentDescription = "Фото сотрудника",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(24.dp))
            )
        }
        Text(text = "Имя: ${employee.firstName}",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(1.dp, 3.dp))
        Text(text = "Фамилия: ${employee.lastName}",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(1.dp, 3.dp))
        Text(text = "Должность: ${employee.prof}",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(1.dp, 3.dp))
        Text(text = "Зарплата: ${employee.salary} рублей в месяц",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(1.dp, 3.dp))
    }

}

@Preview
@Composable
fun EmployeeItemPreview() {
    EmployeeItem(employeeList[6])
}