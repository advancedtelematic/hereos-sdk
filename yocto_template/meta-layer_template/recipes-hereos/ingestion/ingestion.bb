DESCRIPTION = "ingestion" 
SECTION = "hereos"
LICENSE = "CLOSED"
PV = "0.0.1"
PR = "r0"

SRCREV = "02e376e23c4e3041037199c7872db468d3dbd024"
SRC_URI = "git://github.com/advancedtelematic/python-hereos-ingestion;protocol=git;branch=master"

S = "${WORKDIR}/git"

export PYTHONPATH = "../recipe-sysroot-native/usr/lib/python2.7/site-packages"

DEPENDS = "python-grpcio-tools-native"
RDEPENDS_ingestion = "python-protobuf python-grpcio python-requests python-pyyaml"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ingestion ${D}${bindir}
    install -m 0644 ingestion_pb2.py ${D}${bindir}
    install -m 0644 ingestion_pb2_grpc.py ${D}${bindir}
}
