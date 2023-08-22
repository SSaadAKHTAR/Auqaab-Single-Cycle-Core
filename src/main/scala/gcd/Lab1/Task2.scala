package Lab1
import chisel3 . _
import chisel3 . util . _
import java . io . File
class Task2() extends Module {
val io = IO (new Bundle {
 
val reload = Output ( Bool () )

})
val counter = RegInit (0. U(4.W)  )
val max_count = RegInit (4. U (4.W) )

val relode = RegInit(false.B)

when(relode){
counter := counter - 1.U 
 
}
.otherwise{
counter := counter + 1.U 
}
when(counter===max_count){
   io.reload:=1.B
   relode := 1.B
}
.otherwise{
    io.reload:=0.B
}



}
