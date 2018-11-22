SUMMARY = "Runs GRPC ingestion Service"
LICENSE = "CLOSED"

SRC_URI = "file://ingestion.service"
SRC_URI_append = "${@(' file://' + d.getVar('OLP_CREDENTIALS', True)) if d.getVar('OLP_CREDENTIALS', True) else ''}"

SYSTEMD_SERVICE_${PN} = "ingestion.service"

inherit systemd

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    for service in ${SYSTEMD_SERVICE_${PN}}; do
        install -m 0644 ${WORKDIR}/${service} ${D}${systemd_unitdir}/system/

        sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/${service}
    done
    if [ -n "${OLP_CREDENTIALS}" ]; then
        install -m 0700 -d ${D}${localstatedir}/sota
        cp ${OLP_CREDENTIALS} ${D}${localstatedir}/sota/config.yaml
    fi
}

RDEPENDS_${PN} = "ingestion"
