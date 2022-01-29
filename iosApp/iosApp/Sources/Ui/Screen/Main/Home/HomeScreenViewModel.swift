//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Harry on 2022/01/17.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import presentation
import Combine
import KMPNativeCoroutinesCombine

@MainActor
class HomeScreenViewModel: ObservableObject {

    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = HomeScreenViewModelDelegateState(
            isRefreshing: false,
            featuredList: [],
            showNearbyRecommendLocationPermissionBanner: false,
            showNearbyRecommendations: false
    )
    @Published private(set) var effect: HomeScreenViewModelDelegateEffect? = nil

    private let viewModel: HomeScreenViewModelWrapper

    init() {
        
        viewModel = HomeScreenViewModelWrapper()

        createPublisher(for: viewModel.state)
                .receive(on: DispatchQueue.main)
                .sink { _ in
                } receiveValue: { [weak self] value in
                    self?.state = value
                }
                .store(in: &cancellables)

        createPublisher(for: viewModel.effect)
                .receive(on: DispatchQueue.main)
                .sink { _ in
                } receiveValue: { [weak self] value in
                    self?.effect = value
                }
                .store(in: &cancellables)

    }

    func event(e: HomeScreenViewModelDelegateEvent) {
        viewModel.event(e: e)
    }

}
