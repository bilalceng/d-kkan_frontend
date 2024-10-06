import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.components.CustomBottomBar
import com.bilalberekgm.dukkan.components.CustomTopAppBar

@Composable
fun Favourites(
    onBottomBarItemClicked:(Int) -> Unit,
    selectedIndex: Int
) {
    Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        content = {padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = padding.calculateTopPadding(),
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 50.dp
                    ),
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Favourites Screen",
                    style = TextStyle(fontSize = 32.sp)
                )
            }
        },
        bottomBar = {
            CustomBottomBar(selectedIndex = selectedIndex , onBottomBarItemClicked = onBottomBarItemClicked)
        }
    )

}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun FavouritesPreview(){
    Favourites(onBottomBarItemClicked = {}, selectedIndex = 0)
}