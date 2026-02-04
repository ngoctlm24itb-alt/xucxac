package com.example.xucxac
// GIỮ NGUYÊN DÒNG PACKAGE CỦA BẠN (VD: package com.example.diceroller)

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView // Nhớ import thêm cái này để dùng TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Tìm các thành phần bên giao diện (Thêm cái tvResult)
        val rollButton: Button = findViewById(R.id.button)
        val diceImage: ImageView = findViewById(R.id.imageView)
        val resultText: TextView = findViewById(R.id.tvResult)

        // 2. Bắt sự kiện bấm nút
        rollButton.setOnClickListener {
            // Truyền thêm resultText vào hàm để xử lý
            rollDice(diceImage, resultText)
        }
    }

    // Hàm xử lý logic (Nhận vào cả Ảnh và Chữ)
    private fun rollDice(imageView: ImageView, resultText: TextView) {

        // --- PHẦN OBJECT (GIỮ NGUYÊN) ---
        val myDice = Dice(6)
        val diceRoll = myDice.roll()

        // --- XỬ LÝ ẢNH ---
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        imageView.setImageResource(drawableResource)
        imageView.contentDescription = diceRoll.toString()

        // --- CẬP NHẬT DÒNG CHỮ KẾT QUẢ ---
        // Cá nhân hóa thông báo cho vui hơn
        if (diceRoll == 6) {
            resultText.text = "Tuyệt vời! Số 6 may mắn! 🎉"
            resultText.setTextColor(android.graphics.Color.RED) // Đổi màu đỏ cho nổi
        } else {
            resultText.text = "Bạn quay được số: $diceRoll"
            resultText.setTextColor(android.graphics.Color.DKGRAY) // Màu xám bình thường
        }
    }
}

// --- CLASS DICE (GIỮ NGUYÊN) ---
class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}