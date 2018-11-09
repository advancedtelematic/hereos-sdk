DESCRIPTION = "##SERVICE_NAME" 
SECTION = "hereos"
LICENSE = "CLOSED"
PV = "0.0.1"
PR = "r0"

SRC_URI = "file://##SERVICE_NAME-${PV}.tar.gz"

S = "${WORKDIR}/git"

DEPENDS = "grpc grpc-native protobuf-native protobuf-c libnsl2"
RDEPENDS_hello-world = "grpc libnsl2"

EXTRA_OECMAKE = "-DCMAKE_SKIP_RPATH=ON"

inherit pkgconfig cmake

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ##SERVICE_NAME ${D}${bindir}
}
