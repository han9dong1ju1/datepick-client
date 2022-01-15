//
//  SimpleListItem.swift
//  iosApp
//
//  Created by Harry on 2022/01/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SimpleListItem: View {
    
    var title : String
    var subtitle : String? = nil
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(title)
                
                if (subtitle != nil) {
                    Text(subtitle!).font(.subheadline).foregroundColor(.gray)
                }
                
            }
            
            Spacer()
        }
    }
}

struct SimpleListItem_Previews: PreviewProvider {
    static var previews: some View {
        SimpleListItem(title: "App Settings", subtitle: "This is App Setting.")
    }
}
