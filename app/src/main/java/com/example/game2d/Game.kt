import com.example.game2d.Boy
import com.example.game2d.Virus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class Game(val scope: CoroutineScope, val screenW:Int, val screenH: Int,scale:Float) {
    var counter = 0
    val state = MutableStateFlow(0)
    val background = Background(screenW)
    val boy= Boy(screenH,scale)
    val virus = Virus(screenW, screenH, scale)

    var isPlaying = true

    fun Play(){
        scope.launch {
            isPlaying = true

            while (isPlaying) {
                delay(40)
                background.Roll()
                if(counter%3==0){
                    boy.Walk()
                    virus.Fly()
                    //判斷是否碰撞，結束遊戲
                    if(boy.getRect().intersect(virus.getRect())) {
                        isPlaying = false
                    }
                }

                counter++
                state.emit(counter)
            }
        }
    }
}  //
