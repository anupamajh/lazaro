//
//  GuestJiniTextBox.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniTextBox<FieldView>: View where FieldView: View {
    
    var label: String
    
    var body: some View {
        Group {
            fieldView
                .padding(15)
            
        }.foregroundColor(Color("brownishGrey"))
            .font(Fonts.RobotRegularText)
            .overlay(RoundedRectangle(cornerRadius: 5)
                .stroke(Color("veryLightPink"), lineWidth: 1)
                
        
        ).shadow(color: Color("black18"), radius: 1, x: 0, y: 0)
        
        
        
    }
    
    fileprivate init(label: String, fieldView: FieldView) {
        self.label = label
        self.fieldView = fieldView
    }
    
    private let fieldView: FieldView
}

extension GuestJiniTextBox where FieldView == TextField<Text> {
    static func plain(label: String, text: Binding<String>) -> some View {
        return Self(label: label, fieldView:
            TextField(label, text: text)
        )
    }
}

extension GuestJiniTextBox where FieldView == SecureField<Text> {
    static func secure(label: String, text: Binding<String>) -> some View {
        return Self(label: label, fieldView: SecureField(label, text: text))
    }
}

