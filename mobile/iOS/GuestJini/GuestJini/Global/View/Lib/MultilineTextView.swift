//
//  MultilineTextView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 12/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct MultilineTextView: UIViewRepresentable {
    typealias UIViewType = UITextView

    @Binding var text: String
    var onDone: (() -> Void)?

    
    func makeCoordinator() -> Coordinator {
        return Coordinator(text: $text)
    }

    func makeUIView(context: Context) -> UITextView {
       let textField = UITextView()
        textField.delegate = context.coordinator

        textField.isEditable = true
        textField.font = UIFont.preferredFont(forTextStyle: .body)
        textField.isSelectable = true
        textField.isUserInteractionEnabled = true
        textField.isScrollEnabled = true
        if nil != onDone {
            textField.returnKeyType = .done
        }

        return textField
    }

    func updateUIView(_ uiView: UITextView, context: Context) {
        if uiView.text != self.text {
            uiView.text = self.text
        }
        if uiView.window != nil, !uiView.isFirstResponder {
            uiView.becomeFirstResponder()
        }
    }
    
   
    
    final class Coordinator: NSObject, UITextViewDelegate {
        var text: Binding<String>
        var onDone: (() -> Void)?

        init(text: Binding<String>, onDone: (() -> Void)? = nil) {
            self.text = text
            self.onDone = onDone
        }

        func textViewDidChange(_ uiView: UITextView) {
            text.wrappedValue = uiView.text
        }

        func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
            if let onDone = self.onDone, text == "\n" {
                textView.resignFirstResponder()
                onDone()
                return false
            }
            return true
        }
    }
}
