//
//  SettingsView.swift
//  iosApp
//
//  Created by Harry on 2022/01/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SettingsView: View {
    var body: some View {
        List {
            Text("테마 설정")
        }.navigationTitle("설정")
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
