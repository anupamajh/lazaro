//
//  GuestJiniSearchView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniSearchView: View {
    @Binding  var searchText:String
    @Binding var showCancelButton: Bool

    var body: some View {
        HStack {
            HStack {
                TextField("search", text: $searchText, onEditingChanged: { isEditing in
                    self.showCancelButton = true
                }, onCommit: {
                    print("onCommit")
                }).foregroundColor(.primary)

                Button(action: {
                    self.searchText = ""
                }) {
                    Image(systemName: "xmark.circle.fill").opacity(searchText == "" ? 0 : 1)
                }
                
                Button(action: {
                   
                }) {
                     Image(systemName: "magnifyingglass")
                }.padding(.leading)
               
                
            }
            .padding(EdgeInsets(top: 8, leading: 6, bottom: 8, trailing: 15))
            .foregroundColor(.secondary)
            .background(Color.white)
            .cornerRadius(20.0)
        }
        .padding(.horizontal)
        .navigationBarHidden(showCancelButton)
    }
}

struct GuestJiniSearchView_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniSearchView(searchText: .constant(""), showCancelButton: .constant(false))
    }
}
