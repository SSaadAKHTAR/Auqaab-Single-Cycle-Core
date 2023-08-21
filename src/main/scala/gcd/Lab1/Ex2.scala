package Lab1
import chisel3 . util . _
import chisel3._

class Counter() extends Module {
  val io = IO(new Bundle {
    val result = Output(UInt(1.W))
  })

  // val max = (1.U << counterBits) - 1.U
  val count = RegInit(0.U(4.W))

  val MSB = count(3)
  io.result := MSB

  when(count(3) === 1.U) {
    count := 0.U
  }.otherwise {
    count := count + 1.U
  }

  // println(s"Counter created with max value $max")
}

