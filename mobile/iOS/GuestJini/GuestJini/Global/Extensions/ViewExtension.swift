//
//  ViewExtension.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ResignKeyboardOnTap: ViewModifier {
    var gesture = TapGesture().onEnded{_ in
        UIApplication.shared.endEditing(true)
    }
    func body(content: Content) -> some View {
        content.gesture(gesture)
    }
}

extension View {
    func resignKeyboardOnTapGesture() -> some View {
        return modifier(ResignKeyboardOnTap())
    }
}
