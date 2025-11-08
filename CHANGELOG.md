## [0.3.4](https://github.com/dalu-wins/sc-news/compare/v0.3.3...v0.3.4) (2025-11-08)


### Bug Fixes

* update to new api format ([5b4bb08](https://github.com/dalu-wins/sc-news/commit/5b4bb08616f5629ab3fba06ec65cd3e10e0ab763))

## [0.3.3](https://github.com/dalu-wins/sc-news/compare/v0.3.2...v0.3.3) (2025-11-04)


### Bug Fixes

* pull to refresh ([e485d4d](https://github.com/dalu-wins/sc-news/commit/e485d4d07629264f7457d3050f420123ac57a6b5))

## [0.3.2](https://github.com/dalu-wins/sc-news/compare/v0.3.1...v0.3.2) (2025-11-03)


### Bug Fixes

* choppy autoscroll on reload ([f0baaaf](https://github.com/dalu-wins/sc-news/commit/f0baaafca88433aa83637a9defce8ce4a8bcbc41))

## [0.3.1](https://github.com/dalu-wins/sc-news/compare/v0.3.0...v0.3.1) (2025-11-03)


### Bug Fixes

* improve ui ([e1ba9ff](https://github.com/dalu-wins/sc-news/commit/e1ba9ff5a941194624537a1648d38e886a4082e2))
* uncap amount of loaded patches ([93cb64f](https://github.com/dalu-wins/sc-news/commit/93cb64f7d88e6949b897fe9b1b393177e826c653))
* use better section names ([969a320](https://github.com/dalu-wins/sc-news/commit/969a320fe53a1dbeeb9988d2751a02810b34c730))

# [0.3.0](https://github.com/dalu-wins/sc-news/compare/v0.2.1...v0.3.0) (2025-11-03)


### Bug Fixes

* adapt to new api naming scheme ([c3fe253](https://github.com/dalu-wins/sc-news/commit/c3fe253907c58c7be0aa3775ef09717e34a0e9c7))
* added more german translations ([34a04ab](https://github.com/dalu-wins/sc-news/commit/34a04ab2b3ab77dbdd7fa622690f56ce4887098c))
* doubled api calls and error messages, split pinned and !pinned in vm instead of uc's ([f5abd8f](https://github.com/dalu-wins/sc-news/commit/f5abd8f2b8a4098d8646fd16e314a1bb897b9553))
* move pinned vs not pinned patch filtering from Screen to UseCase ([5c41d5a](https://github.com/dalu-wins/sc-news/commit/5c41d5a8fb71cfe3f74ef2476e23e7eabe575b74))
* update ui ([5c6ee36](https://github.com/dalu-wins/sc-news/commit/5c6ee361125a69f513c74dd9d90de6a29da93eb6))


### Features

* transition to new api sided parsing ([7057b7a](https://github.com/dalu-wins/sc-news/commit/7057b7a8bb790f60a5c4ff755ed562e510664a42))

## [0.2.1](https://github.com/dalu-wins/sc-news/compare/v0.2.0...v0.2.1) (2025-11-02)


### Bug Fixes

* german translations ([9f29341](https://github.com/dalu-wins/sc-news/commit/9f29341891bb1745d4104a4b77ec90c9170a95e1))
* timeout on fresh api scrape ([0204423](https://github.com/dalu-wins/sc-news/commit/020442320e72ab270a53fdee1fe0b62b44abf754))

# [0.2.0](https://github.com/dalu-wins/sc-news/compare/v0.1.10...v0.2.0) (2025-10-31)


### Bug Fixes

* race condition in isLoading UI var by loadLocal() vs loadRemote(). make isLoading a loadRemote() indicator only ([cb09e7d](https://github.com/dalu-wins/sc-news/commit/cb09e7d54cdf7e055df5dd5724582b211e8ca7ad))


### Features

* language support german, fix: infinite snackbar queue ([bf85d46](https://github.com/dalu-wins/sc-news/commit/bf85d46bd1b84eae4fc89731a741bb7aab719b71))

## [0.1.10](https://github.com/dalu-wins/sc-news/compare/v0.1.9...v0.1.10) (2025-10-31)


### Bug Fixes

* load from remote on startup ([73240a3](https://github.com/dalu-wins/sc-news/commit/73240a3c7e5e568ee055c2263658d6dc95615238))

## [0.1.9](https://github.com/dalu-wins/sc-news/compare/v0.1.8...v0.1.9) (2025-10-31)


### Bug Fixes

* add error handling for patch feature ([62c9865](https://github.com/dalu-wins/sc-news/commit/62c98653b6fbf402d8f197f96beb825977588b5e))
* animate loading indicator, fade-in fade-out ([f219fb9](https://github.com/dalu-wins/sc-news/commit/f219fb91c38ce5d38335ed9d2318a586c2950a76))
* incorporate error handling for patch feature into PatchEvent.kt ([bf370ca](https://github.com/dalu-wins/sc-news/commit/bf370ca68efde012c80934b37194da7db27eedab))

## [0.1.8](https://github.com/dalu-wins/sc-news/compare/v0.1.7...v0.1.8) (2025-10-30)


### Bug Fixes

* show room notes on startup, before loading from api ([17761b7](https://github.com/dalu-wins/sc-news/commit/17761b7b1ce714d6df88a7c2ceb4d657a9b123f4))

## [0.1.7](https://github.com/dalu-wins/sc-news/compare/v0.1.6...v0.1.7) (2025-10-30)


### Bug Fixes

* move api under subdomain ([0d6211e](https://github.com/dalu-wins/sc-news/commit/0d6211e81c08d1a2694428671bbdae7f4a5a5130))

## [0.1.6](https://github.com/dalu-wins/sc-news/compare/v0.1.5...v0.1.6) (2025-10-30)


### Bug Fixes

* connect to api ([fbdd4be](https://github.com/dalu-wins/sc-news/commit/fbdd4beb0be32ac5dbf7f2259546e6e89ec6aa9f))

## [0.1.5](https://github.com/dalu-wins/sc-news/compare/v0.1.4...v0.1.5) (2025-10-28)


### Bug Fixes

* release with verified status ([c877924](https://github.com/dalu-wins/sc-news/commit/c877924ad60b16b0def639ab02100ad6bbb79091))
* test new branch ([6fa457f](https://github.com/dalu-wins/sc-news/commit/6fa457f521f3f00afc38d9b1d50457d32102d4e5))
* test new branch (2) ([355e385](https://github.com/dalu-wins/sc-news/commit/355e3853e774b538e563b99a29e0b2750f50753f))
* test release build (3) ([192883f](https://github.com/dalu-wins/sc-news/commit/192883fa48761452218a1929a5a3f8931a5a7841))
* test release build (4) ([cb05a86](https://github.com/dalu-wins/sc-news/commit/cb05a866b27a2e963675c94ee1a377f85937d057))
* test release build (5) ([96516ac](https://github.com/dalu-wins/sc-news/commit/96516acc96fd6133d9a07f2ff9bb58b0804d1251))
* test release build (6) ([6ad487c](https://github.com/dalu-wins/sc-news/commit/6ad487c87d5fd3cb7462a38853ef7edfc08594cf))
* test release build (7) ([610d536](https://github.com/dalu-wins/sc-news/commit/610d53653d2b5f9680cec4858b4acdeff9dcb215))

## [0.1.4](https://github.com/dalu-wins/sc-news/compare/v0.1.3...v0.1.4) (2025-10-28)


### Bug Fixes

* commit app/build.gradle.kts instead of package.json ([e2882a0](https://github.com/dalu-wins/sc-news/commit/e2882a0e25bd927e84995ac7ce52003950ad45ee))
* update versionCode as well ([a78311a](https://github.com/dalu-wins/sc-news/commit/a78311ad95acf4f4e7655c1f1f2cf85490d2065d))

## [0.1.3](https://github.com/dalu-wins/sc-news/compare/v0.1.2...v0.1.3) (2025-10-28)


### Bug Fixes

* remove package.json, directly inject version in build.gradle.kts ([bd17f9c](https://github.com/dalu-wins/sc-news/commit/bd17f9cb7324105907f64a396629f2a4fec64c6d))

## [0.1.2](https://github.com/dalu-wins/sc-news/compare/v0.1.1...v0.1.2) (2025-10-28)


### Bug Fixes

* push updates on package.json ([c802425](https://github.com/dalu-wins/sc-news/commit/c802425074d47ac35300c785c73174d5c29ab182))

## [0.1.1](https://github.com/dalu-wins/sc-news/compare/v0.1.0...v0.1.1) (2025-10-28)


### Bug Fixes

* auto bump versions on release ([d5eb1e1](https://github.com/dalu-wins/sc-news/commit/d5eb1e1dcd30cb094069873ae17219f7f42e51c3))
